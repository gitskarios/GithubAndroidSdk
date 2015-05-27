package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Issue;

/**
 * Created by Bernat on 28/05/2015.
 */
public class IssueEventPayload extends GithubEventPayload{
    public Issue issue;
    /*
    0 = {LinkedTreeMap$Node@4742} "url" -> "https://api.github.com/repos/afollestad/material-dialogs/issues/542"
1 = {LinkedTreeMap$Node@4743} "labels_url" -> "https://api.github.com/repos/afollestad/material-dialogs/issues/542/labels{/name}"
2 = {LinkedTreeMap$Node@4744} "comments_url" -> "https://api.github.com/repos/afollestad/material-dialogs/issues/542/comments"
3 = {LinkedTreeMap$Node@4745} "events_url" -> "https://api.github.com/repos/afollestad/material-dialogs/issues/542/events"
4 = {LinkedTreeMap$Node@4746} "html_url" -> "https://github.com/afollestad/material-dialogs/issues/542"
5 = {LinkedTreeMap$Node@4747} "id" -> "8.1014468E7"
6 = {LinkedTreeMap$Node@4748} "number" -> "542.0"
7 = {LinkedTreeMap$Node@4749} "title" -> "Unselected radio buttons in MaterialListPreference, EditText in MaterialEditTextPreference is invisible with non-AppCompat theme"
8 = {LinkedTreeMap$Node@4750} "user" -> " size = 17"
9 = {LinkedTreeMap$Node@4751} "labels" -> " size = 1"
10 = {LinkedTreeMap$Node@4752} "state" -> "closed"
11 = {LinkedTreeMap$Node@4753} "locked" -> "false"
12 = {LinkedTreeMap$Node@4754} "assignee" -> "null"
13 = {LinkedTreeMap$Node@4755} "milestone" -> "null"
14 = {LinkedTreeMap$Node@4756} "comments" -> "3.0"
15 = {LinkedTreeMap$Node@4757} "created_at" -> "2015-05-26T15:30:10Z"
16 = {LinkedTreeMap$Node@4758} "updated_at" -> "2015-05-26T22:59:57Z"
17 = {LinkedTreeMap$Node@4759} "closed_at" -> "2015-05-26T22:59:57Z"
18 = {LinkedTreeMap$Node@4760} "body" -> "When trying to use `MaterialListPreference` in a preference activity derived from `PreferenceActivity` (not appcompat), the unselected option buttons are not visible. Tested in Android 5.1 and 4.1.\r\n\r\n![image](https://cloud.githubusercontent.com/assets/1407837/7815928/fa898824-03fd-11e5-9b33-0042a750c6d0.png)"
     */



}
