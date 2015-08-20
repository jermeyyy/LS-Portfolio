package pl.looksoft.lsportfolio.model;

/**
 * Created by Jermey on 2015-08-18.
 */
public class BaseResponse<DT> {

    private Response<DT> response;

    public Response<DT> getResponse() {
        return response;
    }


    public static class Response<DT> {
        public long status;
        public DT data;

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public DT getData() {
            return data;
        }

        public void setData(DT data) {
            this.data = data;
        }
    }
}