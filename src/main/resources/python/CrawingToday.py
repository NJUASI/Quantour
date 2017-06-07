import tushare as ts

df = ts.get_latest_news()
df.to_csv('f:/news.csv')