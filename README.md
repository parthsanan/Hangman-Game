# Hangman Remastered
*How far can luck take you?*

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
3. <span style="color:red">As a player, I want to be able to pause and resume the game at any time, keeping my state in the game intact.</span>
4. As a player, I want to choose from different difficulty levels to keep the game interesting.
5. <span style="color:yellow">As a player, I want to track my scores for my highest score.</span>

## Implementation
1. Initially, the aspects of the game can be implemented in the console and later can be easily transferred to a GUI.
2. Saving and restoring to the same state would be done by keeping the score as well as the state of the hangman.
3. All the different categories can have a set of words which they can access on the basis of the difficulty and other characteristics.

