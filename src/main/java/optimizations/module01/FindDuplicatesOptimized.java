package optimizations.module01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindDuplicatesOptimized {

    private static final Logger LOG = LoggerFactory.getLogger(FindDuplicatesOptimized.class);

    public List<String> findDuplicates(List<String> items) {
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String item : items) {
            if (!seen.add(item)) {
                duplicates.add(item);
            }
        }
        return new ArrayList<>(duplicates);
    }

    public static void main(String[] args) {

        var list = new ArrayList<String>();
        IntStream.range(0, 1000).forEach(i -> list.add(RandomStringUtils.secure().nextAlphabetic(1)));
        var start = System.currentTimeMillis();
        var instance = new FindDuplicatesOptimized();
        var result = instance.findDuplicates(list);

        LOG.info(result.toString());
        LOG.info("duration: {}",System.currentTimeMillis() - start);
    }

}
