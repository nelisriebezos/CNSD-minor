#!/usr/bin/env python3
"""Some description"""

import requests


def perform_get(baseurl):
    """Perform get request"""

    r = requests.get(baseurl)
    match r.status_code:
        case 200:
            print("Success")
        case 500:
            print("Internal Server Error")
        case 400:
            print("Other status code")


if __name__ == "__main__":
    """Run this when called directly"""
    url1 = 'https://httpbin.org/status/200'
    url2 = 'https://httpbin.org/status/500'
    url3 = 'https://httpbin.org/status/400'

    perform_get(url1)
    perform_get(url2)
    perform_get(url3)
