#!/usr/bin/env python3
"""Some description"""

import requests
import json


def perform_get(baseurl):
    """Perform get request"""
    r = requests.get(baseurl)
    print(json.dumps(r.json(), indent = 3))


if __name__ == "__main__":
    """Run this when called directly"""
    url = 'https://httpbin.org/get'

    perform_get(url)
