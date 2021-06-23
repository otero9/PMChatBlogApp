#
#
# @author a.oteroc@udc.es
#
# API Controller class
#
#
# imports:
from django.http import JsonResponse
from django.template import loader
import json
from Bot import ChatBot as bot
from time import gmtime, strftime

# Solo se admiten peticiones POST con el siguiente body:
#   {
#       "msg" : "Hi!"
#   }
def index(request):
    if request.method == 'POST':
        jsonData = json.loads(request.body.decode('utf-8'))
        msg = jsonData["msg"]
        res = bot.ChatBot.getBot().response(msg)
        time = strftime("%Y-%m-%d %H:%M:%S", gmtime())
        response = JsonResponse({
            "result": "Success",
            "request": msg,
            "response": res,
            "time": time
        })
        response['Access-Control-Allow-Origin'] = '*'
        response['Access-Control-Allow-Methods'] = 'DELETE, POST, GET, OPTIONS'
        response['Access-Control-Allow-Headers'] = 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With'
        return response
    else:
        jsonData = json.loads(request.body.decode('utf-8'))
        response =  JsonResponse({"result": "Bad request"}, status=400)
        response['Access-Control-Allow-Origin'] = '*'
        response['Access-Control-Allow-Methods'] = 'DELETE, POST, GET, OPTIONS'
        response['Access-Control-Allow-Headers'] = 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With'
        return response
