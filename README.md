# Recompose memory leak

**Repo purpose:** Investigation of Compose memory leak.

**Related ticket:** https://issuetracker.google.com/issues/240975572.

**Cause of the issue:** update of mutable state when leaving Composition.

---

**How issue was reproduced on code level:** `MainActivity` contains `textMutableState: MutableStateFlow<String>` and `Composable` function which uses that state. The state is updated `onStop` of activity.

**Steps to reproduce:** open the app -> go to the next screen -> go back -> go to the next screen -> go back -> check AS profiler or wait for LeakCanary report. To speed up the process repeat "go to the next screen -> go back" part few more times.
