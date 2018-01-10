package components;

public class NominationsTable
{
    private String year;
    private String organisation;
    private String award;
    private String work;
    private String result;

    public NominationsTable(String year, String organisation, String award, String work, String result) {
        this.year = year;
        this.organisation = organisation;
        this.award = award;
        this.work = work;
        this.result = result;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
