package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embeddable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class MedicalInfo {
    private long bfr; //blood full range?
    private long fmi; //first medical index?

    //Jackson: json序列化反序列化的java框架(json解析器)
    //Jackson要求: 必须有构造函数, 并且如果有同名构造函数, 则需要将下面这个隐藏的构造函数显示出来
    //也就是说不能只有一个没有参数的constructor
    public MedicalInfo (){}

    public MedicalInfo (long bfr, long fmi) {
        this.bfr = bfr;
        this.fmi = fmi;

    }

}
