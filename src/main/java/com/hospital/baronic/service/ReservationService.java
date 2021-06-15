package com.hospital.baronic.service;

import com.hospital.baronic.domain.Reservation.Reservation;
import com.hospital.baronic.mapper.ReservationMapper;
import com.hospital.baronic.web.dto.ReservationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    // 엑셀 예약 데이터 저장
    public void insertReservationSchedule(ReservationSaveRequestDto reservationSaveRequestDto) throws Exception {
        this.reservationMapper.insertReservationExcelData(reservationSaveRequestDto);
    }

    // 엑셀데이터에서 chart_id와 to do를 파싱하기 위한 함수
    public Reservation parsingReservationExcelDumpData(String dumpData) {

        int chart_id = -1;
        String patient_name = "";
        String todo = "";
        String dump = "";
        dump = dumpData;

        // 할일(to do) 와 chart_id 파싱 시작
        int big_right_boundary = -1; // "]"
        int small_left_boundary_first = -1; // 첫번째 "("
        int small_right_boundary_first = -1; // 첫번째 ")"
        int small_left_boundary_second = -1; // 두번째 ")"
        char[] reservation_content_arr = dump.toCharArray();

        if (dump.contains("temp")) {
            // temp O
            // ex)    [주호성]잇사라펀스켈링신규.(temp1879)\r\n(정수지)
            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == '(') {
                    small_left_boundary_first = i;
//                                chart_id = Integer.parseInt(dump.substring(small_left_boundary_first+5, small_left_boundary_first+9)); // 1879. 이렇게 하면 안되는 형식이 있음.
                    chart_id = -1;
                    break;
                }
            }

            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == ']') {
                    big_right_boundary = i;
                    todo = dump.substring(big_right_boundary+1, small_left_boundary_first); // 잇사라펀스켈링신규.
                    patient_name = dump.substring(big_right_boundary+1, small_left_boundary_first); // 잇사라펀스켈링신규.
                    break;
                }
            }
//                        System.out.println("--------- temp O ---------");
//                        System.out.println("chart_id : " + chart_id + " todo : " + todo);

        } else {
            // temp X
            // ex)    [주호성]피타야콤(02448)\r\nTel:010(하악DBS+#36 B.pit)"
            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == ']') {
                    big_right_boundary = i;
                    break;
                }
            }

            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == '(') {
                    small_left_boundary_first = i;
                    break;
                }
            }

            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == ')') {
                    small_right_boundary_first = i;
                    break;
                }
            }

            try {
                patient_name = dump.substring(big_right_boundary + 1, small_left_boundary_first);
            } catch (NumberFormatException e) {
                patient_name = "error_name";
            }

            try {
                chart_id = Integer.parseInt(dump.substring(small_left_boundary_first + 1, small_right_boundary_first));
            } catch (NumberFormatException e) {
                chart_id = -1;
            }

            String reservation_content_after = "";
            reservation_content_after = dump.substring(small_left_boundary_first+1);
            char[] reservation_content_arr_after = reservation_content_after.toCharArray();

            for (int i = 0 ; i < reservation_content_arr_after.length ; i++) {
                if (reservation_content_arr_after[i] == '(') {
                    small_left_boundary_second = i;
                    break;
                }
                reservation_content_after.substring(small_left_boundary_second+1);
            }

            todo = reservation_content_after.substring(small_left_boundary_second+1, reservation_content_after.length()-2);
//                        System.out.println("---------temp X ---------");
//                        System.out.println("chard_id : " + chart_id + " todo : " + todo);
        }

        Reservation inputReservationData = new Reservation(patient_name, chart_id, todo, dump);
//        inputReservationData.builder()
//                .chart_id(chart_id)
//                .todo(todo)
//                .dump(dump)
//                .build();

        return inputReservationData;

    }
}


