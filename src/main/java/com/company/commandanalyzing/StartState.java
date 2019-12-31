package com.company.commandanalyzing;

class StartState implements State
{
    public StartState(CommandLineAnalyzer analyzer)
    {
        this.analyzer = analyzer;
    }

    @Override
    public void processCommand(String command)
    {
        if (command.equals("--files"))
        {
            analyzer.setFilesState();
        }
        else if (command.equals("--links"))
        {
            analyzer.setLinksState();
        }
        else
        {
            throw new IllegalArgumentException("Unexpected argument \"" + command
                    + "\", expected flag \"--files\" or \"--links\"");
        }
    }

    private CommandLineAnalyzer analyzer;
}
