/**
 * @author Vinod Parlapalli
 * created on 2019/12/06
 *
 */

package in.rgukt.r081247.bankingapi.model.type;

public enum TransactionType {
    DEPOSIT("DEPOSIT"),
    TRANSFER("TRANSFER"),
    WITHDRAW("WITHDRAW");

    private String code;

    private TransactionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
