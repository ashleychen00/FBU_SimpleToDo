# Project 1 - *SimpleToDo App*

**SimpleToDo** is an Android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Ashley Chen**

Time spent: **6** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **view a list of todo items**
* [x] User can **successfully add and remove items** from the todo list
* [x] User's **list of items persisted** upon modification and and retrieved properly on app restart

The following **stretch** features are implemented:

* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list

The following **additional** features are implemented:

* [x] Added an if-else statement in the onAddItem function to prevent empty items from taking up space in the list. 
* [x] Added an additional Toast saying "Good job!" when removing an item.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

(https://www.youtube.com/watch?v=lR4Dfqv5nh8&list=UU-rwjzRblJ1oWMr_Xtq4rlg&index=3)

GIF created with [Recordit](http://recordit.co/).

(Originally was a GIF, but then issues occurred so I figured a Youtube video would be easier.)

## Notes

A small challenge throughout the process was working with instructions that were slightly out of date due to the nature of Android development.
It was also conceptually hard to grasp the different ways my Main Activity handled the various event listeners, from the weird seemingly doubly-layered anonymous class and callback
function to remove an item, to the xml format onAddItem function done for the button.
It was cool to see how both of these methods differed from the methods origianlly introduced in the Hello World app. 
Overall, it felt like we had to all of the methods and hints provided through the IDE and all the imported packages without
fully knowing why or how the code for those members worked. 
This was especially true for the stretch goal, since I never heard of or worked with Intent before. 

## License

    Copyright [2019] [Ashley Chen]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
