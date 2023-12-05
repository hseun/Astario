import requests
from bs4 import BeautifulSoup
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
import schedule
import time

cred = credentials.Certificate('astario-e7543-firebase-adminsdk-hxvgd-76274684b9.json')
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://astario-e7543-default-rtdb.firebaseio.com/'
})

urlList = ["https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EB%AC%BC%EB%B3%91%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EB%AC%BC%EA%B3%A0%EA%B8%B0%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%96%91%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%ED%99%A9%EC%86%8C%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%8C%8D%EB%91%A5%EC%9D%B4%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EA%B2%8C%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%82%AC%EC%9E%90%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%B2%98%EB%85%80%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%B2%9C%EC%B9%AD%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%A0%84%EA%B0%88%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%82%AC%EC%88%98%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8",
           "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%97%BC%EC%86%8C%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8"]


def crawling():
    for site in urlList:
        try:
            headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36'}
            responses = requests.get(site, headers=headers)
            responses.raise_for_status()

            soup = BeautifulSoup(responses.text, "html.parser")
            craw_name = soup.find("li", {"class": "first_lst"})
            craw_during = soup.find("span", {"class": "con_date"})
            craw_content = soup.find("p", {"class": "_cs_fortune_text"})
            icon_url = soup.find("div", {"class": "thumb"}).find("img").get("src")

            name = craw_name.text.strip()
            during = craw_during.text.strip()
            content = craw_content.text.strip()

            database = db.reference(name)

            database.update({'name': name, 'during': during, "content": content, "iconUrl": icon_url})
            print(name)
            print(during)
            print(content)
            print(icon_url)
            print()
        except requests.exceptions.RequestException as e:
            print(f"RequestException: {e}")

        time.sleep(5)


schedule.every().day.at("00:05").do(crawling)
