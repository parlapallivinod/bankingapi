/**
 * @author Vinod Parlapalli
 * created on 2019/12/06
 *
 */

package in.rgukt.r081247.bankingapi.model;

public enum TransactionStatus {
    FAILURE("FAILURE"),
    SUCCESS("SUCCESS");

    private String code;

    private TransactionStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
