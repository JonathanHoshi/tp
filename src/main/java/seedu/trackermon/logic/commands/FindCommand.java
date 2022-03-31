package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.trackermon.commons.core.Messages;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Show;

/**
 * Finds and lists all shows in Trackermon whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all shows whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "At least one parameter must be stated in the find command.\n"
            + "Parameters: [n/NAME] [s/STATUS] [r/RATING] [t/TAG]…\u200B\n"
            + "Example: " + COMMAND_WORD + " hero kyojin academia OR "
            + COMMAND_WORD + " n/hero kyojin academia s/completed t/Action";
    public static final String TAG_ERROR = "Tag parameter must only be a single word.\n"
            + "Example: " + COMMAND_WORD + " t/Action";
    public static final String RATING_ERROR = "Rating parameter must not be empty and range from 0 to 5!\n"
            + "Example: " + COMMAND_WORD + " r/5";

    private final Predicate<Show> predicate;

    public FindCommand(Predicate<Show> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredShowList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_SHOWS_LISTED_OVERVIEW, model.getFilteredShowList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

