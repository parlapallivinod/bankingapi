package in.rgukt.r081247.bankingapi.model.converter;

import in.rgukt.r081247.bankingapi.model.TransactionType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
    @Override
    public String convertToDatabaseColumn(TransactionType type) {
        return type == null ? null : type.getCode();
    }

    @Override
    public TransactionType convertToEntityAttribute(String type) {
        if (type == null)
            return null;

        return Stream.of(TransactionType.values())
                .filter(t -> t.getCode().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
