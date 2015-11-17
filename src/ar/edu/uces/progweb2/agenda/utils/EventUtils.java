package ar.edu.uces.progweb2.agenda.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ar.edu.uces.progweb2.agenda.dto.DargEventDTO;
import ar.edu.uces.progweb2.agenda.helper.EventHelper;

public class EventUtils {
	
	public static int getTop(String startTime){
		EventHelper position = getMapTop().get(startTime);
		return position.getTop();
	}
	
	public static int getHeight(String startTime, String endTime){
		EventHelper	startPosition = getMapTop().get(startTime);
		EventHelper endPosition = getMapTop().get(endTime);
		
		return getMapHeiht().get(endPosition.getNum() - startPosition.getNum());
	}
	
	private static Map<String, EventHelper> getMapTop(){
		Map <String, EventHelper> map = new HashMap<String, EventHelper>();
		map.put("00:00", new EventHelper(1, 0));
		map.put("00:30", new EventHelper(2, 20));
		map.put("01:00", new EventHelper(3, 40));
		map.put("01:30", new EventHelper(4, 60));
		map.put("02:00", new EventHelper(5,80));
		map.put("02:30", new EventHelper(6,100));
		map.put("03:00", new EventHelper(7,120));
		map.put("03:30", new EventHelper(8,140));
		map.put("04:00", new EventHelper(9,160));
		map.put("04:30", new EventHelper(10,180));
		map.put("05:00", new EventHelper(11,200));
		map.put("05:30", new EventHelper(12,220));
		map.put("06:00", new EventHelper(13,240));
		map.put("06:30", new EventHelper(14,260));
		map.put("07:00", new EventHelper(15,280));
		map.put("07:30", new EventHelper(16,300));
		map.put("08:00", new EventHelper(17,320));
		map.put("08:30", new EventHelper(18,340));
		map.put("09:00", new EventHelper(19,360));
		map.put("09:30", new EventHelper(20,380));
		map.put("10:00", new EventHelper(21,400));
		map.put("10:30", new EventHelper(22,420));
		map.put("11:00", new EventHelper(23,440));
		map.put("11:30", new EventHelper(24,460));
		map.put("12:00", new EventHelper(25,480));
		map.put("12:30", new EventHelper(26,500));
		map.put("13:00", new EventHelper(27,520));
		map.put("13:30", new EventHelper(28,540));
		map.put("14:00", new EventHelper(29,560));
		map.put("14:30", new EventHelper(30,580));
		map.put("15:00", new EventHelper(31,600));
		map.put("15:30", new EventHelper(32,620));
		map.put("16:00", new EventHelper(33,640));
		map.put("16:30", new EventHelper(34,660));
		map.put("17:00", new EventHelper(35,680));
		map.put("17:30", new EventHelper(36,700));
		map.put("18:00", new EventHelper(37,720));
		map.put("18:30", new EventHelper(38,740));
		map.put("19:00", new EventHelper(39,760));
		map.put("19:30", new EventHelper(40,780));
		map.put("20:00", new EventHelper(41,800));
		map.put("20:30", new EventHelper(42,820));
		map.put("21:00", new EventHelper(43,840));
		map.put("21:30", new EventHelper(44,860));
		map.put("22:00", new EventHelper(45,880));
		map.put("22:30", new EventHelper(46,900));
		map.put("23:00", new EventHelper(47,920));
		map.put("23:30", new EventHelper(48,940));
		return map;
	}
	private static Map<Integer, Integer> getMapHeiht(){
		Map <Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 20);
		map.put(2, 40);
		map.put(3, 60);
		map.put(4, 80);
		map.put(5, 100);
		map.put(6, 120);
		map.put(7, 140);
		map.put(8, 160);
		map.put(9, 180);
		map.put(10, 200);
		map.put(11, 220);
		map.put(12, 240);
		map.put(13, 260);
		map.put(14, 280);
		map.put(15, 300);
		map.put(16, 320);
		map.put(17, 340);
		map.put(18, 360);
		map.put(19, 380);
		map.put(20, 400);
		map.put(21, 420);
		map.put(22, 440);
		map.put(23, 460);
		map.put(24, 480);
		map.put(25, 500);
		map.put(26, 520);
		map.put(27, 540);
		map.put(28, 560);
		map.put(29, 580);
		map.put(30, 600);
		map.put(31, 620);
		map.put(32, 640);
		map.put(33, 660);
		map.put(34, 680);
		map.put(35, 700);
		map.put(36, 720);
		map.put(37, 740);
		map.put(38, 760);
		map.put(39, 780);
		map.put(40, 800);
		map.put(41, 820);
		map.put(42, 840);
		map.put(43, 860);
		map.put(44, 880);
		map.put(45, 900);
		map.put(46, 920);
		map.put(47, 940);
		return map;
	}

	public static int getIndex(List<DargEventDTO> eventsDtos, String startTime) {
		int cont = 1;
		for (DargEventDTO event : eventsDtos){
			if(event.getStartTime().equals(startTime)){
				cont++;
			}
		}
		return cont;
	}

	public static String getTime(int top) {
		String time="";
		Map<String, EventHelper> map = getMapTop();
	    for (Entry<String, EventHelper> e: map.entrySet()) {
	         EventHelper event = e.getValue();
	         if(event.getTop() == top){
	        	 time = e.getKey();
	        	 break;
	         }
	    }
	    return time;
	}
}


