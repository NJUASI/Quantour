import tushare as ts
import datetime
import os

def _crawing_history_front(codes,filePath):
    for code in codes:
        print(code)
        startDate = datetime.datetime.strptime(code[1],'%Y-%m-%d')
        endDate = datetime.datetime.strptime(code[2],'%Y-%m-%d')
        gap = endDate.date().year - startDate.date().year

        if(gap<=3):
            print('time is short!!!!')
            print('--------------------date2------------------')
            df = ts.get_h_data(code=code[0], start=code[1], end=code[2])
            df.to_csv(filePath+'date2/'+code[0]+'.csv')
            print(filePath +'date2/'+ code[0] + '.csv')
        else:
            print('time is long!!!!')
            print('--------------------date1------------------')
            df = ts.get_h_data(code=code[0], start=code[1], end='2013-12-31')
            df.to_csv(filePath +'date1/'+ code[0] + '.csv')
            print(filePath + 'date1/' + code[0] + '.csv')

            print('--------------------date2------------------')
            df = ts.get_h_data(code=code[0], start='2014-01-01', end=code[2])
            df.to_csv(filePath + 'date2/' + code[0] + '.csv')
            print(filePath + 'date2/' + code[0] + '.csv')

def _crawing_history_after(codes,filePath):
    for code in codes:
        print(code)
        startDate = datetime.datetime.strptime(code[1], '%Y-%m-%d')
        endDate = datetime.datetime.strptime(code[2], '%Y-%m-%d')
        gap = endDate.date().year - startDate.date().year

        if (gap <= 3):
            print('time is short!!!!')
            print('--------------------date2------------------')
            df = ts.get_h_data(code=code[0],autype='hfq',start=code[1], end=code[2])
            df.to_csv(filePath + 'date2/' + code[0] + '.csv')
            print(filePath + 'date2/' + code[0] + '.csv')
        else:
            print('time is long!!!!')
            print('--------------------date1------------------')
            df = ts.get_h_data(code=code[0],autype='hfq',start=code[1], end='2013-12-31')
            df.to_csv(filePath + 'date1/' + code[0] + '.csv')
            print(filePath + 'date1/' + code[0] + '.csv')

            print('--------------------date2------------------')
            df = ts.get_h_data(code=code[0],autype='hfq',start='2014-01-01', end=code[2])
            df.to_csv(filePath + 'date2/' + code[0] + '.csv')
            print(filePath + 'date2/' + code[0] + '.csv')

def _crawing_industry(filePath):
    df = ts.get_industry_classified()
    df.to_csv(filePath)

def _crawing_area(filePath):
    df = ts.get_area_classified()
    df.to_csv(filePath)

def _read_codes():
    file = open("f:/stockData.txt",encoding='UTF-8')
    codes = []
    for line in file.readlines():
        subCodes = []
        subCodes.append(line.split(';')[0].strip())
        subCodes.append(line.split(';')[4].strip())
        subCodes.append(line.split(';')[5].strip())
        codes.append(subCodes)
    return codes

def getBaseCodes():
    return ['000300','399001','399005','399006']

def getCodes():
    codes = _read_codes()
    baseCodes = getBaseCodes()
    result = [];
    for code in codes:
        if code not in baseCodes:
            result.append(code)
    return result

def file_name(file_dir):
    for root, dirs, files in os.walk(file_dir):
       return files

def getCrawedCode(filepath):
    filelist = file_name(filepath)
    codes = []
    for file in filelist:
        codes.append(file.split('.')[0])
    return codes

def getCrawingCode(filepath):
    crawedCodes = getCrawedCode(filepath)
    codes = getCodes()
    result = []
    for code in codes:
        if code[0] not in crawedCodes:
            result.append(code)
    return result

# print(getCrawingCode('f:/Quant/adjData/stock/front/date2/'))
# baseCodes = ['000300','000001','399001','399005','399006']
# codes = getCrawingCode('f:/Quant/adjData/stock/after/date2/')
# print(codes)
# _crawing_history_front(codes,'f:/Quant/adjData/stock/front/')
# _crawing_history_after(codes,'f:/Quant/adjData/stock/after/')
#
# _crawing_history_front_base(baseCodes,'f:/Quant/adjData/baseStock/front/')
# _crawing_history_after_base(baseCodes,'f:/Quant/adjData/stock/after/')
_crawing_area('f:/Quant/area.csv')
_crawing_industry('f:/Quant/industry.csv')