import os
token = os.environ['X-SF-TOKEN']


def get_headers():
    return {'Content-Type': "application/json", 'X-SF-TOKEN': token}
