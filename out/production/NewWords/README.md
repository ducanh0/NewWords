# newWords
app support learn English words

information : https://drive.google.com/file/d/1VOIo0rRgQULL4CYqT6XWD0wBRJ6E3zMH/view 
    -
          [0] Exit
          [1] Add
          [2] Remove
          [3] Update
          [4] Display
          [5] Lookup
          [6] Search ( Trie )
          [7] Game ( game bắn từ Đức Anh )
          [8] Import from file
          [9] Export to file

  Quy tắc code : 
  - 
     [0] phải comment trước mọi hàm , ghi hàm đó làm gì , các biến tham số đóng vai trò gì 
     [1] đặt tên biến dài , có nghĩa 
     [2] code cách thưa chút cho dễ nhìn , debug
     [3] sửa cái gì thì nên viết vào file readme github này cho mn đều nắm được 
- format hàm adjustWord:
    [1] : nhập từ tiếng anh  (ng dùng phải nhập đúng từ trong từ điển , không thì cho người dùng nhập lại)
    [2] : hiển thị 02 lựa chọn để người dùng chọn
       -
             [1]. sửa từ tiếng anh đó (lấy tập nghĩa của từ cũ trong cây trie rồi xóa từ cũ đi , thêm từ mới mà người dùng muốn với tập nghĩa cũ đó)
             [2]. sửa nghĩa tiếng việt
              cái này sẽ hiển thị 03 lựa chọn cho người dùng chọn
                 -
                       [1]. thêm 01 nghĩa mới
                       [2] xóa 01 nghĩa cũ (nếu nghĩa người dùng nhập không tồn tại thì in không tồn tại và cho người ta nhập lại nếu muốn)
                       [3]. sửa 01 nghĩa cũ sang 01 nghĩa mới (cũng bắt ng dùng phải nhập đúng nghĩa cũ thì mới xóa nghĩa cũ , không thì kệ , nghĩa mới thì thêm vào là xong thôi)

  lưu ý : chỉ xử lý đúng 01 thao tác một lúc cho đơn giản , người dùng kiểu muốn sửa hay xóa hay thêm nhiều lần thì thực hiện nhiều lần
                
              
