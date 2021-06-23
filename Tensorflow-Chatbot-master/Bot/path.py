#
#
# @author a.oteroc@udc.es
#
# Path class
#
# imports:
import inspect, os

def getJsonPath():
    path = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
    path = os.path.join(path, 'content.json').replace("\\", "/")
    return path


def getPath(file):
    path = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
    path = os.path.join(path, file).replace("\\", "/")
    return path
