# Hangman Remastered

_How far can luck take you?_

Experience a twist on the classic game, built with Java.

## Description

Hangman Remastered is a modern take on the classic word-guessing game. Players try to uncover a hidden word by guessing its letters.

With multiple genres of varying difficulties and a timed mode, it removes all the potentially boring aspects of the game.

## Features

- Choose from a variety of word categories, including countries, movies and foods.
- Track your progress with scoring.
- Select difficulty levels: easy, rookie, master.
- Customize the game settings to your preferences, including word length and number of attempts.
- Each player gets 3 lives and after every 2 levels users get a hint which they can use if they are ever stuck on a word.

## User Stories

1. As a player, I want to start a new game and guess the hidden word letter by letter.
2. As a player, I want to see my progress in the game, including the hangman drawing and the letters I've guessed.
3. As a player, I want to track my scores for my highest score.
4. As a player, I want to choose from different difficulty levels to keep the game interesting.
5. As a user, I want to be able to save my current hangman game (after it is over) to file (if I so choose).
6. As a user, I want to be able to be able to load all my hangman games ever played from file (if I so choose).

## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple HangmanGames to a GameManager" by choosing to save a game after it is over.
- You can generate the second required action related to the user story "loading and saving the state of the application" by loading the saved games in the History Tab.
- You can locate my visual component by navigating to view the hangman while the game is played.
- You can save the state of the application by pressing save after finishing a game.
- You can load all the previously played games by navigating to the Load Games option in the History Tab.

## Phase 4: Task 2

Sample Output when Window closed:

- Tue Apr 02 23:29:16 PDT 2024 </br>
  Game saved with secret word: piano

- Tue Apr 02 23:29:24 PDT 2024 </br>
  Games filtered by word: piano

## Phase 4: Task 3

### Refactoring Suggestions

#### 1. Singleton Design Pattern for GamesManager

Consider implementing the Singleton Design Pattern for the `GamesManager` class. As of now, I accidently have a rough implementation of the Singleton pattern, but by enforcing a single instance of `GamesManager` throughout the application, we can centralize control over game management.

#### 2. Introduce a Game Logic Handler

To enhance the separation of concerns and improve maintainability, introduce a new class (e.g., `GameLogicHandler`) responsible for managing the game logic. This class would encapsulate tasks such as managing game states, checking guesses, and determining game outcomes. By offloading these responsibilities from the `GameWindow` class, we can achieve a cleaner separation of concerns, making the codebase easier to understand, test, and maintain. This refactoring promotes better adherence to the Single Responsibility Principle (SRP) and improves the overall design of the application architecture.
