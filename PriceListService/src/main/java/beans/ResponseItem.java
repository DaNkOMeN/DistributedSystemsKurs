package beans;

public class ResponseItem {

    private boolean status;
    private String problem_description;

    public ResponseItem(boolean status, String problem_description) {
        this.status = status;
        this.problem_description = problem_description;
    }

    public ResponseItem(){
        this.status = true;
        this.problem_description = "Default response";
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getProblem_description() {
        return problem_description;
    }

    public void setProblem_description(String problem_description) {
        this.problem_description = problem_description;
    }
}
