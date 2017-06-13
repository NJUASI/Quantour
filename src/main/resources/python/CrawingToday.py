import tushare as ts
import os
import sys


def _crawing_area(filePath):
    df = ts.get_area_classified()
    df.to_csv(filePath)


def _get_root():
    task_path = os.path.dirname(sys.argv[0]) + os.path.sep + 'taskInfo.txt'
    print(task_path)
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


# file = open('f:/info.txt','w+')
# file.write(_get_root())
# _crawing_area(_modify_path(_get_root())+'area.csv')
print('hello')
