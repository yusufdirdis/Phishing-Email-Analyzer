package scoring;

import model.AnalysisResult;
import java.util.ArrayList;

public class RiskScorer {
    public AnalysisResult score (ArrayList<String> flags){
        int tempScore = 0;
        for(String flag : flags) {
            if (flag.contains("Possible typosquatting:")){
                tempScore+= 10;
            }
            else if(flag.contains("Suspicious keyword in URL")){
                tempScore+= 3;
            }
            else if(flag.contains("Insecure URL:")){
                tempScore += 8;
            }
            else if(flag.contains("Suspicious URL (contains numbers):")){
                tempScore += 3;
            }
            /* commented out because i dont know the name of the flags yet.
            else if(flag.contains("Suspicious Keyword:") {
                tempScore += 3; //tentative
            }
             */
        }
        return new AnalysisResult(tempScore);
    }
}
