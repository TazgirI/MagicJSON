package net.tazgirl.magicjson.subscription.base;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StatementPriorityQueue
{
    public Map<ExecutableAddress, Integer> priorityQueue;

    public StatementPriorityQueue(Map<ExecutableAddress, Integer> priorityQueue)
    {
        this.priorityQueue = priorityQueue;
    }

    public void Run()
    {
        List<ExecutableAddress> sortedAddresses = highToLow();

        for(ExecutableAddress address : sortedAddresses)
        {
            address.Run();
        }
    }

    public void Run(Map<String, Object> args)
    {
        List<ExecutableAddress> sortedAddresses = highToLow();

        for(ExecutableAddress address : sortedAddresses)
        {
            address.Run(args);
        }
    }


    public List<ExecutableAddress> highToLow()
    {
        return priorityQueue.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(Map.Entry::getKey).toList();
    }
}
