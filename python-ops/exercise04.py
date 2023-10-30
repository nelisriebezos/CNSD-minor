#!/usr/bin/env python3
"""Some description"""

import requests


def perform_get(baseurl):
    """Perform get request"""

    req = requests.get(baseurl)
    parse_data(req.json())


def parse_data(data):
    """Parse the returned data"""
    print(data)
    
    print(f"The title is: {data['slideshow']['title']}")
    print(f"The author is: {data['slideshow']['author']}")
    print(f"It was published on: {data['slideshow']['date']}")
    slide_titles = map(lambda slide: slide["title"], data["slideshow"]["slides"])

    print("It contains the following slides:")
    for slide_title in slide_titles:
        print(f"- {slide_title}")

if __name__ == "__main__":
    """Run this when called directly"""
    url = 'https://httpbin.org/json'

    perform_get(url)
