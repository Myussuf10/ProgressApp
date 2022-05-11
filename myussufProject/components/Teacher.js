import React from 'react';
import {
  KeyboardAvoidingView,
  StyleSheet,
  Text,
  View,
  TextInput,
  TouchableOpacity
} from 'react-native';

import {Avatar} from '@rneui/themed';
import { ListItem } from '@rneui/base';

const Teacher = () => {
  return (
    <ListItem>
    <Avatar rounded 
    source={{
        uri: 
        "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",}}/>
    </ListItem>
  );
};

export default Teacher;

const styles = StyleSheet.create({
  
});