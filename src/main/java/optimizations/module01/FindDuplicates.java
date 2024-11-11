package optimizations.module01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindDuplicates {

    private static final Logger LOG = LoggerFactory.getLogger(FindDuplicates.class);

    public List<String> findDuplicates(List<String> items) {
        List<String> duplicates = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).equals(items.get(j))
                    && !duplicates.contains(items.get(i))) {
                    duplicates.add(items.get(i));
                }
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        var list = new ArrayList<String>();
        IntStream.range(0, 1000).forEach(i -> list.add(RandomStringUtils.secure().nextAlphabetic(1)));

        var start = System.currentTimeMillis();
        var instance = new FindDuplicates();
        var result = instance.findDuplicates(list);
        LOG.info(result.toString());
        LOG.info("duration: " + (System.currentTimeMillis() - start));
    }


}
