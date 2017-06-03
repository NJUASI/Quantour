package com.edu.nju.asi.spider.Model;

/**
 * Created by Harvey on 2017/5/15.
 *
 * 用于保存爬取到的正确名称、简称、所属市场类型
 */
public class Code_Name {

    public Code_Name(String code, String name, String spell, String type) {
        this.code = code;
        this.name = name;
        this.spell = spell;
        this.type = type;
    }

    /**
     * 代码
     */
    String code;
    /**
     * 名称
     */
    String name;

    /**

     * 简称
     */
    String spell;
    /**
     * 市场类型
     */
    String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
