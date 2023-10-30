#!/usr/bin/env python3
"""Some description"""

import requests
import json


def perform_post(baseurl):
    """Perform post request"""

    post_data = {
        "course": "Python Training", 
        "name": "Exercise02"
    }

    headers = {
        'User-Agent': 'Nelis',
    }

    r = requests.post(baseurl, data=post_data, headers=headers)
    print(json.dumps(r.json(), indent = 3))



if __name__ == "__main__":
    """Run this when called directly"""
    url = 'https://httpbin.org/post'

    perform_post(url)
