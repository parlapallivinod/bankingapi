/*
package in.rgukt.r081247.bankingapi.model.converter;

import in.rgukt.r081247.bankingapi.model.TransactionStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, String> {
    @Override
    public String convertToDatabaseColumn(TransactionStatus status) {
        return status == null ? null : status.getCode();
    }

    @Override
    public TransactionStatus convertToEntityAttribute(String status) {
        if (status == null)
            return null;

        return Stream.of(TransactionStatus.values())
                .filter(s -> s.getCode().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
*/
