package optimizations.module01;

import java.util.ArrayList;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executor {

    private static final Logger LOG = LoggerFactory.getLogger(Executor.class);

    public static void main(String[] args) {
        var findDuplicates = new FindDuplicates();
        var findDuplicatesOptimized = new FindDuplicatesOptimized();

        var list = new ArrayList<String>();
        IntStream.range(0, 1000).forEach(i -> list.add(RandomStringUtils.secure().nextAlphabetic(1)));

        var start = System.currentTimeMillis();
        findDuplicates.findDuplicates(list);
        var end = System.currentTimeMillis();
        LOG.info("normal version: {}", end - start);

        start = System.currentTimeMillis();
        findDuplicatesOptimized.findDuplicates(list);
        end = System.currentTimeMillis();
        LOG.info("optimized version: {}", end - start);
    }

}
