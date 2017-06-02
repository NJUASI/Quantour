import tushare as ts

def _crawing_history_front(codes,startDate,endDate,filePath):
    for code in codes:
        df = ts.get_h_data(code,start=startDate,end=endDate)
        df.to_csv(filePath)

def _crawing_history_front_base(codes,startDate,endDate,filePath):
    for code in codes:
        df = ts.get_h_data(code,start=startDate,end=endDate,index=True)
        df.to_csv(filePath)

def _crawing_history_after(codes,startDate,endDate,filePath):
    for code in codes:
        df = ts.get_h_data(code,autype='hfq',start=startDate, end=endDate)
        df.to_csv(filePath)

def _crawing_history_after_base(codes,startDate,endDate,filePath):
    for code in codes:
        df = ts.get_h_data(code,autype='hfq',start=startDate,end=endDate,index=True)
        df.to_csv(filePath)