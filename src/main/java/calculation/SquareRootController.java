package calculation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

@RestController
public class SquareRootController {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @RequestMapping("/sqroot")
    public Double Sqroot(@RequestParam(value="data", defaultValue="") String[] data) {

        if(data != null && data.length != 0){

            //Make sure it is numeric values from input.
            if(Arrays.stream(data).anyMatch(n -> !n.matches("-?\\d+(\\.\\d+)?")))
                return 0.0d;

            Double top3SqrSum = Arrays.stream(data).mapToDouble(Double::parseDouble).boxed().sorted(Comparator.reverseOrder()).limit(3)
                                .map(n -> Math.pow(n,2))
                                .reduce(0.0d, (a, b) -> a + b);

            return Double.valueOf(df2.format(Math.sqrt(top3SqrSum)));
        }

        return 0.0d;

    }
}
