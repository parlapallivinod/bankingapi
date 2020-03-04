package in.rgukt.r081247.bankingapi.model.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
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
