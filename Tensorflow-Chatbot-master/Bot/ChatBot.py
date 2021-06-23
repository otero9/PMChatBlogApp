#
#
# @author a.oteroc@udc.es
#
# Chatbot class
#
#
# imports:
import nltk
import ssl
try:
    _create_unverified_https_context = ssl._create_unverified_context
except AttributeError:
    pass
else:
    ssl._create_default_https_context = _create_unverified_https_context
nltk.download('punkt')
from nltk.stem.lancaster import LancasterStemmer
from nltk import word_tokenize,sent_tokenize
import numpy as np
import tflearn
import random
import pickle
import json
from Bot import path
#from nltk.corpus import stopwords
#stop_words = stopwords.words('english')


# Stop words
ignore_words = ['?','!','¿','¡',',','.',';',':','¨','ª','º','*','_','-','`','^','#','·','¬','|','{','}','(',')','[',']','Ç']

class ChatBot(object):

    instance = None

    @classmethod
    def getBot(cls):
        if cls.instance is None:
            cls.instance = ChatBot()
        return cls.instance

    def __init__(self):
        if self.instance is not None:
            raise ValueError("Did you forgot to call getBot function ? ")
        # se instancia LancasterStemmer
        self.stemmer = LancasterStemmer()
        # se carga el modelo entrenado
        data = pickle.load(open(path.getPath('trained_data'), "rb"))
        # se carga obtienen las palabras
        self.words = data['words']
        # se carga obtienen las clases
        self.classes = data['classes']
        # se carga obtienen la matriz de entrada
        train_x = data['train_x']
        # se carga obtienen las matrices de salida
        train_y = data['train_y']
        
        with open(path.getJsonPath()) as json_data:
            self.intents = json.load(json_data)
        # insertamos los datos entrenados en una red
        net = tflearn.input_data(shape=[None, len(train_x[0])])
        # se crea una capa con 16 unidades /neuronas ocultas y completamente conectadas
        net = tflearn.fully_connected(net, 16)
        # se crea una capa con 16 unidades /neuronas ocultas y completamente conectadas
        net = tflearn.fully_connected(net, 16)
        net = tflearn.fully_connected(net, len(train_y[0]), activation='softmax')
        # se realiza un analisis de regresion lineal sobre la red neuronal
        net = tflearn.regression(net)
        # se define el modelo y el tensorboard
        self.model = tflearn.DNN(net, tensorboard_dir=path.getPath('train_logs'))
        # se guarda el modelo
        self.model.load(path.getPath('model.tflearn'))

    def clean_up_sentence(self, sentence):
        sentence_words = nltk.word_tokenize(sentence)
        #print(stop_words)
        sentence_words = [self.stemmer.stem(word.lower()) for word in sentence_words  if word not in ignore_words]
        return sentence_words

    def bow(self, sentence, words, show_details=False):
        # se tokeniza la entrada (request)
        sentence_words = self.clean_up_sentence(sentence)
        #print(sentence_words)
        bag = [0] * len(words)
        for s in sentence_words:
            for i, w in enumerate(words):
                if w == s:
                    bag[i] = 1
                    if show_details:
                        print("found in bag: %s" % w)
        return np.array(bag)

    def classify(self, sentence):
        #print(sentence)
        ERROR_THRESHOLD = 0.25
        results = self.model.predict([self.bow(sentence, self.words)])[0]
        results2 = [[i, r] for i, r in enumerate(results) if r > ERROR_THRESHOLD]
        if len(results2) > 0:
            results2.sort(key=lambda x: x[1], reverse=True)
            results = results2
        else:
            results2 = [[i, r] for i, r in enumerate(results) if r > 0.05]
            results2.sort(key=lambda x: x[1], reverse=True)
            results = results2

        return_list = []
        for r in results:
            return_list.append((self.classes[r[0]], r[1]))
        return return_list

    def response(self, sentence, userID='111', show_details=False):
        results = self.classify(sentence)
        context = {}
        if results:
            while results:
                for i in self.intents['intents']:
                    if i['tag'] == results[0][0]:
                        #print(i)
                        if 'context_set' in i:
                            if show_details: print('context:', i['context_set'])
                            context[userID] = i['context_set']
                        if not 'context_filter' in i or \
                                (userID in context and 'context_filter' in i and i['context_filter'] == context[userID]):
                            if show_details: print('tag:', i['tag'])
                            return random.choice(i['responses'])
                return "I can't guess"
