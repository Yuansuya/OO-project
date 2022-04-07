# OO-project
Objecy-Oriented analysis and design Project

## The Spec of UML editor

本UML編輯器總共分成3個部分，第一個部分是編輯的區域稱之為Canvas area也就是所謂的畫布。編輯區域左邊有一排按鈕。
所需完成的按鈕有6個，依上到下分別為
* select 
* association
* generaliztion
* composition
* class
* use case

這些按鈕除了select之外都是用來在編輯區內創造一個UML物件

## UML editor requirement 
使用此UML editor的情況可分為幾大類
1. 建立物件
2. 連結物件
3. 選取物件
4. 群組物件

---

Definitions:
* basic object: 如class或use case物件
* connection line: 如各種association lines
* composite object: 此物件是由多個基本物件經過group功能組合而成，他可以是一個樹狀的結構，即被group的物件可以再被group成新的composite object
* depth: 每個物件均有一個深度值0~99，深度值較小者會覆蓋在深度值較大者之上，且會攔截落於該物件的mouse事件，總結來說只有最上方的物件會接收到mouse事件

---

UseCase A.1 Creating an UML object
