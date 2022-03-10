package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResultTrackermon;
import seedu.address.logic.commands.CommandTrackermon;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TrackermonParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelTrackermon;
import seedu.address.model.ReadOnlyShowList;
import seedu.address.model.show.Show;
import seedu.address.storage.StorageTrackermon;



/**
 * The main LogicManager of the app.
 */
public class LogicManagerTrackermon implements LogicTrackermon {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManagerTrackermon.class);

    private final ModelTrackermon model;
    private final StorageTrackermon storage;
    private final TrackermonParser parser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManagerTrackermon(ModelTrackermon model, StorageTrackermon storage) {
        this.model = model;
        this.storage = storage;
        parser = new TrackermonParser();
    }

    @Override
    public CommandResultTrackermon execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResultTrackermon commandResult;
        CommandTrackermon command = parser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveShowList(model.getShowList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyShowList getShowList() {
        return model.getShowList();
    }

    @Override
    public ObservableList<Show> getFilteredShowList() {
        return model.getFilteredShowList();
    }

    @Override
    public Path getShowListFilePath() {
        return model.getShowListFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}