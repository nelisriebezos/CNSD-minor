#!/usr/bin/env python3
"""Some description"""

import requests


def perform_get(baseurl):
    """Perform get request"""

    req = requests.get(baseurl)
    parse_data(req.json())


def parse_data(data):
    """Parse the returned data"""

    # implement this


if __name__ == "__main__":
    """Run this when called directly"""
    url = 'https://httpbin.org/json'

    perform_get(url)
