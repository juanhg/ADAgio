\version "2.16.2"
\score {
 <<
\new Staff{

\tempo 4=90
\clef treble
\time 3/4
\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { a'4.\mf } \\ { s4. c''8.\mf } \\ { s4. e''8.\mf } \\ { s2 s16 a'8.\mf } \\ { s2. } \\ >>
<< { a'4. } \\ { s4. c''8. } \\ { s4. e''8. } \\ { s2 s16 a'8. } \\ { s2. } \\ >>
<< { a'4. } \\ { s4. c''8. } \\ { s4. e''8. } \\ { s2 s16 a'8. } \\ { s2. } \\ >>

\time 4/4
<< { b'2 } \\ { s2 dis''4 } \\ { s2 fis''4 } \\ { s2. a'4 } \\ { s1 } \\ >>
<< { b'2 } \\ { s2 d''4 } \\ { s2 fis''4 } \\ { s2. b'4 } \\ { s1 } \\ >>

}
\new Staff{
s2.
s2.
s2.

\set Staff.midiMinimumVolume = #0
\set Staff.midiMaximumVolume = #1.0
\set Staff.midiInstrument = #"acoustic grand"
<< { b'2\mf } \\ { s2 dis''4\mf } \\ { s2 fis''4\mf } \\ { s2. a'4\mf } \\ { s1 } \\ >>
<< { b'2 } \\ { s2 d''4 } \\ { s2 fis''4 } \\ { s2. b'4 } \\ { s1 } \\ >>

}
>> 
\layout{ }
\midi {
\context {
\Score 
tempoWholesPerMinute = #(ly:make-moment 72 2)
}
}
}