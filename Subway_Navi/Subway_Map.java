package Subway_Navi;

import java.util.*;

class Subway_Map {

    result Map(String[] subwayMap, String start, String end) {
        Map<String, ArrayList<Station_info>> station_map = new HashMap<>();
        for (String list : subwayMap) {
            String[] part = list.split(",");
            String start_Station = part[0];
            String end_Station = part[1];
            int time = Integer.parseInt(part[2]);

            station_map.computeIfAbsent(start_Station, k -> new ArrayList<>()).add(new Station_info(end_Station, time));
            station_map.computeIfAbsent(end_Station, k -> new ArrayList<>()).add(new Station_info(start_Station, time));
        }

        Map<String, String> prv = new HashMap<>();           // 경로 출력을 위한 이전의 역을 저장
        Map<String, Integer> distance = new HashMap<>();     // 비용 계산 및 경로 확인
        PriorityQueue<Station_info> q = new PriorityQueue<>(Comparator.comparingInt(s -> s.time));
        ArrayList<Station_info> neighbors; // 현재 역의 인접 역을 저장
        q.offer(new Station_info(start, 0));

        while (!q.isEmpty()) {
            Station_info current = q.poll(); // 가장 적은 소요시간을 가진 역을 반환

            if (distance.containsKey(current.station))   // 이미 현재역의 소요시간이 계산됐는지를 확인
                continue;

            distance.put(current.station, current.time);    // 현재역의 소요시간 확인하겠음

            if (current.station.equals(end))    // 도착역에 도착했으면 반복문 탈출
                break;

            neighbors = station_map.get(current.station);   // 현재역의 인접역들을 대입
            if (neighbors != null) {
                for (Station_info neighbor : neighbors) {
                    int min = current.time + neighbor.time;     //현재역에서 인접역의 소요시간 계산
                    if (min < distance.getOrDefault(neighbor.station, -1) || !distance.containsKey(neighbor.station)) { //
                        q.offer(new Station_info(neighbor.station, min));
                        prv.put(neighbor.station, current.station);
                    }
                }
            }
        }

        return path(start, end, prv, distance.getOrDefault(end, -1));
    }

    result path(String start, String end, Map<String, String> prv, int time) {
        Stack<String> path = new Stack<>();
        int t = time;

        if (t != -1) {    // 경로가 있으면
            path.push(end);
            String temp = end;
            while (!path.peek().equals(start)) {
                temp = prv.get(temp);
                path.push(temp);
            }  // 마지막에 대입된 값이 출발역과 같으면 탈출
        }
        return new result(time, path);
    }

    class result {
        int time;
        Stack<String> station;

        result(int time, Stack<String> station) {
            this.time = time;
            this.station = station;
        }
    }

    class Station_info {
        String station;
        int time;

        Station_info(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }
}
