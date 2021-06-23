#
#
# @author a.oteroc@udc.es
#
# Train chatbot class
#
# imports:
import nltk # Natural language toolkit
from nltk.stem.lancaster import LancasterStemmer # stemming algorithm (transformar una palabra a su raiz)
import tensorflow as tf # Tensorflow
from tensorflow.python.framework import ops
import tflearn # TensorFlow Deep Learning Library
# util imports:
import numpy as np
import random
import pickle
import path
import json
from nltk.corpus import stopwords

# se instancia LancasterStemmer
stemmer = LancasterStemmer()
# se carga el contenido del fichero 'content.json'
with open(path.getJsonPath()) as json_data:
    intents = json.load(json_data)

# instanciamos arrays
words = []
classes = []
documents = []
# Stop words
ignore_words = ['?','!','¿','¡',',','.',';',':','¨','ª','º','*','_','-','`','^','#','·','¬','|','{','}','(',')','[',']','Ç']

# se recorren todos los intents
for intent in intents['intents']:
    for pattern in intent['patterns']:
        # se tokeniza cada sentencia de patterns
        w = nltk.word_tokenize(pattern)
        words.extend(w)
        # se anade tag y sentencia a documents
        documents.append((w, intent['tag']))
        if intent['tag'] not in classes:
            # se anade cada tag a classes
            classes.append(intent['tag'])

# stemizamos -> se deriva cada palabra a su rais, salvo las stop words
words = [stemmer.stem(w.lower()) for w in words if w not in ignore_words]

# se ordena el resultado de palabras obtenido
words = sorted(list(set(words)))
# se ordena el resultado de clases obtenido
classes = sorted(list(set(classes)))

# loguear resultado:
#print(len(documents), "Docs", documents)
#print(len(classes), "Classes", classes)
#print(len(words), "Split words", words)

data_set = []
output_empty = [0] * len(classes)

for document in documents:
    bag = []
    # stem the pattern words for each document element
    pattern_words = document[0]
    pattern_words = [stemmer.stem(word.lower()) for word in pattern_words]
    # Creamos bolsa de palabras
    for w in words:
        bag.append(1) if w in pattern_words else bag.append(0)
    # output is a '0' for each intent and '1' for current intent
    output_row = list(output_empty)
    output_row[classes.index(document[1])] = 1
    data_set.append([bag, output_row])

# se baraja aleatoriamente la lista data_set
random.shuffle(data_set)
# se convierte a array numpy
data_set = np.array(data_set)
# se crea el data_set y las test lists
train_x = list(data_set[:, 0])
train_y = list(data_set[:, 1])

# se limpia y resetea el grafo
ops.reset_default_graph()

# Build neural network
# se introducen los datos de entrada en la red neuronal con tensores de una sola dimension y de longitud marcada por train_x
net = tflearn.input_data(shape=[None, len(train_x[0])])
# se crea una capa con 16 unidades /neuronas ocultas y completamente conectadas
net = tflearn.fully_connected(net, 16)
# se crea una capa con 16 unidades / neuronas ocultas y completamente conectadas
net = tflearn.fully_connected(net, 16)
net = tflearn.fully_connected(net, len(train_y[0]), activation='softmax')
# se realiza un analisis de regresion lineal sobre la red neuronal
net = tflearn.regression(net)
# se define el modelo y el tensorboard
model = tflearn.DNN(net, tensorboard_dir=path.getPath('train_logs'))
# se entrena el modelo con un recorrido de 20000 y un tamano de batch de 500
model.fit(train_x, train_y, n_epoch=20000, batch_size=500, show_metric=True)
# se guarda el modelo entrenado
model.save(path.getPath('model.tflearn'))
# se guarda la lista de palabras, clases, documentos, entradas, salidas y el modelo entrenado
pickle.dump({'words': words, 'classes': classes, 'train_x': train_x, 'train_y': train_y},
            open(path.getPath('trained_data'), "wb"))
