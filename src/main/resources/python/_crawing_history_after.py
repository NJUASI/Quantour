import tushare as ts
import os


def _crawing_history(codes, filePath):
    for code in codes:
        print(code)
        print('--------------------start------------------')
        df = ts.get_h_data(code=code[0].strip(), autype='hfq', start=code[1].strip(), end=code[2].strip())
        df.to_csv(filePath + os.path.sep + code[0].strip() + '.csv')
        print(filePath + os.path.sep + code[0].strip() + '.csv')
        print('--------------------end------------------')


def _read_codes(path):
    file = open(path, encoding='utf_8_sig')
    codes = []
    for line in file.readlines():
        subCodes = []
        subCodes.append(line.split(';')[0].strip())
        subCodes.append(line.split(';')[4].strip())
        subCodes.append(line.split(';')[5].strip())
        codes.append(subCodes)
    return codes


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


def _get_root():
    task_path = os.path.abspath('.') + os.path.sep + 'taskInfo.txt'
    file = open(task_path, encoding='utf_8_sig')
    for line in file.readlines():
        if line.split('=')[0].strip() == 'root':
            return line.split('=')[1].strip()
    return 'none'


root = _modify_path(_get_root())
codes = _read_codes(root + os.path.sep + 'stockData.txt')
_crawing_history(codes, root + os.path.sep + 'adjData' + os.path.sep + 'after')
