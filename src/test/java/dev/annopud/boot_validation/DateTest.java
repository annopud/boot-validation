package dev.annopud.boot_validation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonTest
class DateTest {



    @Test
    void testDate() {
        String str = "2023-01-01 00:00:00.0";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        System.out.println(dateTime);

        LocalDateTime now = LocalDateTime.of(2023, 1,1,1,1,1, 900_000_000);
        String newDateStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(newDateStr);
    }


    @Data
    static class TestRequest {
        private String customerId;
        private String rmId;
        private String rmNameTh;
        private String rmNameEn;
        private String licenseNumber;
        private String transactionOrderId;
        private String channel;
        @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private LocalDateTime transactionDate;
        private String investmentType;
    }

    @Autowired
    private JacksonTester<TestRequest> testRequestJacksonTester;

    @Test
    void testInputDate() throws IOException {

        String json = """
            {"transactionDate": "2023-01-01 00:00:00"}
            """;

        TestRequest testRequest = testRequestJacksonTester.parseObject(json);

        String json1 = testRequestJacksonTester.write(testRequest).getJson();
        System.out.println();
    }

    @Value
    public static class ImmutableOb {

        String customerId;
        String rmId;
        String rmNameTh;
        String rmNameEn;
        String licenseNumber;
        String transactionOrderId;
        String channel;

//        @Builder.Default()
        List<String> myList;

        @Builder
        public ImmutableOb(
            String customerId,
            String rmId,
            String rmNameTh,
            String rmNameEn,
            String licenseNumber,
            String transactionOrderId,
            String channel,
            List<String> myList
        ) {
            this.customerId = customerId;
            this.rmId = rmId;
            this.rmNameTh = rmNameTh;
            this.rmNameEn = rmNameEn;
            this.licenseNumber = licenseNumber;
            this.transactionOrderId = transactionOrderId;
            this.channel = channel;
            this.myList = myList == null ? List.of() : List.copyOf(myList);
        }
    }


    @Autowired
    JacksonTester<ImmutableOb> immutableObJacksonTester;

    // test immutable object
    @Test
    void testImmutableObject() throws IOException {


        ImmutableOb ob = ImmutableOb.builder()
            .myList(new ArrayList<>(List.of("a", "b", "c")))
//            .customerId("123")
            .build();


        String json = immutableObJacksonTester.write(ob).getJson();
        System.out.println(json);

        ImmutableOb immutableOb = immutableObJacksonTester.parseObject(json);

//        List<String> myList = ob.getMyList();
//        myList.add("d"); // This will affect the original list in the ImmutableOb

        System.out.println(immutableOb);
    }

    @Value
    public static class ImmutableDate {

        LocalDateTime transactionDate;

        @Builder
        public ImmutableDate(LocalDateTime transactionDate) {
            this.transactionDate = transactionDate;
        }
    }

    @Autowired
    JacksonTester<ImmutableDate> immutableDateJacksonTester;

    @Test
    void testImmutableDate() throws IOException {
        ImmutableDate immutableDate = ImmutableDate.builder()
            .transactionDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
            .build();

        LocalDateTime localDateTime = immutableDate.getTransactionDate().minusDays(100);// This will not affect the original date in the ImmutableDate

        String json = immutableDateJacksonTester.write(immutableDate).getJson();
        System.out.println(json);

        ImmutableDate parsedImmutableDate = immutableDateJacksonTester.parseObject(json);
        System.out.println(parsedImmutableDate);
    }
}
