package com.quicktutorial.learnmicroservices.accountMicroservices.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.quicktutorial.learnmicroservices.accountMicroservices.common.utility.Utility.fromLocalDateTimeToLastUpdate;

@Data
@NoArgsConstructor
public class SourceUpdate {

    String source;
    String timestamp;

    public SourceUpdate(String source, LocalDateTime lastUpdate) {
        this.source = source;
        this.timestamp = fromLocalDateTimeToLastUpdate(lastUpdate);
    }

    public SourceUpdate(String source, String timestamp) {
        this.source = source;
        this.timestamp = timestamp;
    }
}
