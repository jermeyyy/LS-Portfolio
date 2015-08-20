package pl.looksoft.lsportfolio.model;

/**
 * Created by Jermey on 2015-08-18.
 */
public class BaseResponse<DT> {

    private int status;
    private DT data;

    public long getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DT getData() {
        return data;
    }

    public void setData(DT data) {
        this.data = data;
    }
}