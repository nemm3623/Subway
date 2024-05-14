package Subway_Navi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Navi_listener implements ActionListener{
    String[] subwayMap = new String[]{"서울역(1),시청(1),2", "서울역(1),서울역(4),5",
            "시청(1),시청(2),5", "시청(1),종각(1),3", "종각(1),종로3가(1),1", "종로3가(1),종로3가(3),5",
            "종로3가(1),종로5가(1),2", "종로5가(1),동대문(1),2", "동대문(1),동대문(4),5", "동대문(1),동묘앞(1),1",
            "동묘앞(1),신설동(1),2", "신설동(1),제기동(1),2", "제기동(1),청량리(1),2", "시청(2),을지로입구(2),2",
            "을지로입구(2),을지로3가(2),2", "을지로3가(2),을지로3가(3),5", "을지로3가(2),을지로4가(2),1",
            "을지로4가(2),동대문운동장(2),2", "동대문운동장(2),동대문운동장(4),5", "동대문운동장(2),신당(2),2",
            "신당(2),상왕십리(2),2", "상왕십리(2),왕십리(2),1", "왕십리(2),한양대(2),2", "한양대(2),뚝섬(2),2",
            "뚝섬(2),성수(2),1", "성수(2),건대입구(2),2", "건대입구(2),구의(2),3", "구의(2),강변(2),1",
            "강변(2),성내(2),3", "성내(2),잠실(2),2", "잠실(2),신천(2),2", "신천(2),종합운동장(2),2",
            "종합운동장(2),삼성(2),2", "삼성(2),선릉(2),2", "선릉(2),역삼(2),2", "역삼(2),강남(2),1",
            "강남(2),교대(2),2", "교대(2),교대(3),5", "교대(2),서초(2),2", "서초(2),방배(2),2", "방배(2),사당(2),3",
            "사당(2),사당(4),5", "사당(2),낙성대(2),2", "낙성대(2),서울대입구(2),2", "서울대입구(2),봉천(2),2",
            "봉천(2),신림(2),2", "신림(2),신대방(2),2", "신대방(2),구로디지털단지(2),2", "구로디지털단지(2),대림(2),2",
            "대림(2),신도림(2),3", "신도림(2),문래(2),2", "문래(2),영등포구청(2),2", "영등포구청(2),당산(2),2",
            "당산(2),합정(2),4", "합정(2),홍대입구(2),2", "홍대입구(2),신촌(2),2", "신촌(2),이대(2),2",
            "이대(2),아현(2),2", "아현(2),충정로(2),2", "충정로(2),시청(2),3", "지축(3),구파발(3),4",
            "구파발(3),연신내(3),2", "연신내(3),불광(3),2", "불광(3),녹번(3),2", "녹번(3),홍제(3),3",
            "홍제(3),무악재(3),2", "무악재(3),독립문(3),2", "독립문(3),경복궁(3),2", "경복궁(3),안국(3),2",
            "안국(3),종로3가(3),2", "종로3가(3),을지로3가(3),2", "을지로3가(3),충무로(3),1", "충무로(3),충무로(4),5",
            "충무로(3),동대입구(3),2", "동대입구(3),약수(3),2", "약수(3),금호(3),1", "금호(3),옥수(3),2",
            "옥수(3),압구정(3),2", "압구정(3),신사(3),3", "신사(3),잠원(3),2", "잠원(3),고속터미널(3),2",
            "고속터미널(3),교대(3),2", "교대(3),남부터미널(3),2", "남부터미널(3),양재(3),3", "양재(3),매봉(3),2",
            "매봉(3),도곡(3),2", "도곡(3),대치(3),2", "대치(3),학여울(3),1", "학여울(3),대청(3),2",
            "대청(3),일원(3),2", "일원(3),수서(3),3", "당고개(4),상계(4),2", "상계(4),노원(4),2",
            "노원(4),창동(4),2", "창동(4),쌍문(4),2", "쌍문(4),수유(4),3", "수유(4),미아(4),2",
            "미아(4),미아삼거리(4),2", "미아삼거리(4),길음(4),2", "길음(4),성신여대입구(4),3",
            "성신여대입구(4),한성대입구(4),2", "한성대입구(4),혜화(4),2", "혜화(4),동대문(4),2", "동대문(4),동대문운동장(4),2",
            "동대문운동장(4),충무로(4),2", "충무로(4),명동(4),1", "명동(4),회현(4),2", "회현(4),서울역(4),2",
            "서울역(4),숙대입구(4),2", "숙대입구(4),삼각지(4),2", "삼각지(4),신용산(4),1", "신용산(4),이촌(4),2",
            "이촌(4),동작(4),4", "동작(4),총신대입구(4),3", "총신대입구(4),사당(4),2", "사당(4),남태령(4),1"
    };
    JTextField start_Station;
    JTextField end_Station;
    JTextField path;
    JTextField time;
    Navi_listener(JTextField start, JTextField end,JTextField p, JTextField t){
        start_Station = start;
        end_Station = end;
        path=p;
        time=t;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Subway_Map map = new Subway_Map();
        Subway_Map.result result = map.Map(subwayMap,start_Station.getText(),end_Station.getText());

        if(result.station.isEmpty()) path.setText("경로가 없습니다.");
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(result.station.pop());
            while (!result.station.isEmpty()){
                sb.append(" -> ");
                sb.append(result.station.pop());
            }
            String all_path = sb.toString();
            path.setText(all_path);
        }
        time.setText(String.valueOf(result.time));
    }
}

public class Navigation {
    public static void main(String[] args) {
        JFrame frame = new JFrame("서울시 자하철 네비게이션");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,400));

        JTextField start_Station = new JTextField("출발 역 입력 Ex) 서울역(1) ");
        start_Station.setBounds(125,50,150,30);

        JTextField end_Station = new JTextField("도착 역 입력 Ex) 서울역(1) ");
        end_Station.setBounds(125,100,150,30);

        JTextField path = new JTextField("최단 경로 출력");
        path.setBounds(75,150,250,30);

        JTextField time = new JTextField("총 소요시간 출력");
        time.setBounds(125,200,150,30);

        JButton input_btn = new JButton("입력");
        input_btn.setBounds(150,270,100,30);

        Navi_listener navi = new Navi_listener(start_Station,end_Station,path,time);
        input_btn.addActionListener(navi);

        panel.setLayout(null);
        panel.add(start_Station);
        panel.add(end_Station);
        panel.add(input_btn);
        panel.add(path);
        panel.add(time);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setVisible(true);

    }
}
