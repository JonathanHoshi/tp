package seedu.address.logic.parser;

import static seedu.address.commons.core.MessagesTrackermon.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.MessagesTrackermon.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommandTrackermon;
import seedu.address.logic.commands.CommandTrackermon;
import seedu.address.logic.commands.DeleteCommandTrackermon;
import seedu.address.logic.commands.ExitCommandTrackermon;
import seedu.address.logic.commands.HelpCommandTrackermon;
import seedu.address.logic.commands.ListCommandTrackermon;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TrackermonParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public CommandTrackermon parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    HelpCommandTrackermon.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommandTrackermon.COMMAND_WORD:
            return new AddCommandParserTrackermon().parse(arguments);

        case DeleteCommandTrackermon.COMMAND_WORD:
            return new DeleteCommandParserTrackermon().parse(arguments);

        case ListCommandTrackermon.COMMAND_WORD:
            return new ListCommandTrackermon();

        case ExitCommandTrackermon.COMMAND_WORD:
            return new ExitCommandTrackermon();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}