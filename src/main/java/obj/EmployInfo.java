package obj;

import obj.pcard.PCardInfo;
import obj.pcard.PCardList;
import obj.leave.Leave;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class EmployInfo {
    // 工号
    String id;

    // 职位
    String position;

    // 姓名
    String name;


    // 请假
    ArrayList<Leave> leaves;

    // 打卡信息
    PCardList cardList;
}
