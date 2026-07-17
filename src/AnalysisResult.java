package model;

public class AnalysisResult {
    public int score;
    public String riskLevel;

    public AnalysisResult(int score){
        this.score = score;
        if(score == 0){
            riskLevel = "None";
        }
        else if(score < 10){
            riskLevel = "Low";
        }
        else if(score < 20){
            riskLevel = "Medium";
        }
        else {
            riskLevel = "High";
        }
    }
}
