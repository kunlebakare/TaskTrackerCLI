TaskTrackerCLI is a simple commandline tool for managing your daily tasks.\
**Made for engineers** \
If you're an engineer who spends most of their time in the command line environment, this is the perfect tool for you. \
No need to go back and forth between your phone and your pc. Simply add your tasks and manage them here easily.

_Created as part of a learning project for the Java roadmap on [Roadmap.sh](https://roadmap.sh/projects/task-tracker)_

 
  # HOW TO USE
  In your favourite command line app, type `task-cli` in combination with any of `add` `update` `delete` to add, update, or delete a task respectively.
  e.g. `task-cli add "go to the gym by 5"`
  
  Do `task-cli list` to list all tasks and their respective task-id

  To update a task description, do `task-cli update <task-id> <new-task-description>`, e.g. `task-cli update 1 "go to the gym by 6"`
  
  Do `task-cli delete <task-id>` to delete a task. E.g, `task-cli delete 1`

  You can update the status of your tasks as in-progress or done by doing `task-cli mark-in-progress <task-id>` or `task-cli mark-done <task-id>`, respectively.

  You can also do `task-cli list in-progress` and `task-cli list done` to list all in-progress or done tasks

  You can always do `task-cli` or `task-cli help` to list the available commands and their description.


  # HOW TO CONTRIBUTE
  Fork this repo, make your changes or additions, and make a pull request.

  # HOW TO REPORT A BUG
  1. Check first for existing issues incase your bug has already been reported
  2. Create a new issue, be as detailed as possible. Include steps to reproduce bug if possible.
  3. Submit the issue
  

  
