import tushare as ts
import os
import sys


def _crawing_industry(filePath):
    df = ts.get_industry_classified()
    df.to_csv(filePath)


def _get_root():
    task_path = os.path.dirname(sys.argv[0]) + os.path.sep + 'taskInfo.txt'
    file = open(task_path, encoding='utf_8_sig')
    for line in file.readlines():
        if line.split('=')[0].strip() == 'root':
            return line.split('=')[1].strip()
    return 'none'


def _modify_path(filepath):
    if filepath.find('/', 0, len(filepath)) != -1:
        infos = filepath.split('/')
        result = infos[0].strip() + os.path.sep
        count = 1
        while count < len(infos) - 1:
            result = result + infos[count].strip() + os.path.sep
            count += 1
        result = result + infos[len(infos) - 1].strip()
        return result

    if filepath.find('\\', 0, len(filepath)) != -1:
        infos = filepath.split('\\')
        result = infos[0].strip() + os.path.sep
        count = 1
        while count < len(infos) - 1:
            result = result + infos[count].strip() + os.path.sep
            count += 1
        result = result + infos[len(infos) - 1].strip()
        return result
    return 'none'

_crawing_industry(_modify_path(_get_root())+'industry.csv')