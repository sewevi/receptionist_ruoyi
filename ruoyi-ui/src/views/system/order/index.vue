<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable size="small">
          <el-option v-for="dict in orderStatus" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="订单类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择订单类型" clearable size="small">
          <el-option v-for="dict in orderType" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <!--<el-button-->
        <!--type="primary"-->
        <!--plain-->
        <!--icon="el-icon-plus"-->
        <!--size="mini"-->
        <!--@click="handleAdd"-->
        <!--&gt;新增</el-button>-->
      </el-col>
      <!--<el-col :span="1.5">-->
      <!--<el-button-->
      <!--type="success"-->
      <!--plain-->
      <!--icon="el-icon-edit"-->
      <!--size="mini"-->
      <!--:disabled="single"-->
      <!--@click="handleUpdate"-->
      <!--&gt;修改</el-button>-->
      <!--</el-col>-->
      <!--<el-col :span="1.5">-->
      <!--<el-button-->
      <!--type="success"-->
      <!--plain-->
      <!--icon="el-icon-edit"-->
      <!--size="mini"-->
      <!--:disabled="single"-->
      <!--@click="handleView"-->
      <!--&gt;查看</el-button>-->
      <!--</el-col>-->
      <!--<el-col :span="1.5">-->
      <!--<el-button-->
      <!--type="danger"-->
      <!--plain-->
      <!--icon="el-icon-delete"-->
      <!--size="mini"-->
      <!--:disabled="multiple"-->
      <!--@click="handleDelete"-->
      <!--v-hasPermi="['system:order:remove']"-->
      <!--&gt;删除</el-button>-->
      <!--</el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['system:order']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" label="序号" align="center" prop="id" />
      <el-table-column label="标题" align="center" prop="title" />
      <!--<el-table-column label="内容" align="center" prop="content" />-->
      <el-table-column label="地址" align="center" prop="address" />
      <!--<el-table-column label="图片地址" align="center" prop="img" />-->
      <el-table-column label="订单状态" align="center" prop="status" >
        <template slot-scope="scope">
          <span>{{ scope.row.status === '0' ? "未开始": scope.row.status === '1' ? "开始中" : "已完成" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="付款状态" align="center" prop="status" >
        <template slot-scope="scope">
          <span>{{ scope.row.payStatus === '0' ? "未付款" : "已付款" }}</span>
        </template>
      </el-table-column>
      <!--<el-table-column label="订单类型" align="center" prop="type" />-->
      <!--<el-table-column label="收纳师主键" align="center" prop="receptionId" />-->
      <el-table-column label="收纳师名称" align="center" prop="receptionName" />
      <el-table-column label="收纳师联系方式" align="center" prop="receptionTel" />
      <!--<el-table-column label="评论" align="center" prop="comment" />-->
      <!--<el-table-column label="备注" align="center" prop="remark" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:order']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="view(scope.row)"
            v-hasPermi="['system:order']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:order']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="receiveUpdate(scope.row)"
            v-hasPermi="['system:order']"
          >领取订单</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="标题" prop="title" >
          <el-input v-model="form.title" placeholder="请输入标题" :disabled="type === 'view'"/>
        </el-form-item>
        <el-form-item label="内容">
          <el-input  v-model="form.content" :min-height="192" :disabled="type === 'view'"/>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" :disabled="type === 'view' "/>
        </el-form-item>
        <el-form-item label="图片地址" prop="img" >
          <el-image :src="form.img" v-model="form.img" placeholder="请输入图片地址" :disabled="type === 'view'"
                    style="height: 175px;width: 175px"/>
        </el-form-item>
        <!--<el-form-item label="订单状态">-->
        <!--<el-select v-model="form.status" >-->
        <!--<el-option v-for="dict in orderStatus " :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>-->
        <!--</el-select>-->
        <!--</el-form-item>-->
        <el-form-item label="订单类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择订单类型">
            <el-option v-for="dict in  orderType" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" :disabled="type === 'view'" />
          </el-select>
        </el-form-item>
        <el-form-item label="预约时间" prop="reserveTime"  >
          <el-input v-model="moment(form.reserveTime).format('YYYY-MM-DD HH:mm:ss')" :disabled="true"/>
        </el-form-item>
        <el-form-item label="收纳师名称" prop="receptionName">
          <el-input v-model="form.receptionName" placeholder="请输入收纳师名称" :disabled="type === 'view'" />
        </el-form-item>
        <el-form-item label="收纳师联系方式" prop="receptionTel">
          <el-input v-model="form.receptionTel" placeholder="请输入收纳师联系方式":disabled="type === 'view'" />
        </el-form-item>
        <el-form-item label="评论" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入评论" :disabled="type === 'view'" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" :disabled="type === 'view'" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-if="type !== 'view'">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder } from "@/api/system/order";
  import moment from "moment"

  export default {
    name: "Order",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 导出遮罩层
        exportLoading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 订单表格数据
        orderList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          title: null,
          outTradeNo: null,
          orderTime: null,
          time: null,
          amount: null,
          status: null,
          type: null,
          payStatus: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        },
        // 订单状态字典值
        orderStatus: [],
        type: '',
        // 订单类型字典纸
        orderType: []
      };
    },
    created() {
      console.log(this.$store.getters);
      this.getList();
      this.getDicts("order_status").then(response => {
        this.orderStatus = response.data;
      });
      this.getDicts("order_type").then(response => {
        this.orderType = response.data;
      })
    },
    methods: {
      moment,
      /** 查询订单列表 */
      getList() {
        this.loading = true;
        listOrder(this.queryParams).then(response => {
          this.orderList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          title: null,
          outTradeNo: null,
          content: null,
          address: null,
          orderTime: null,
          reserveTime:null,
          time: null,
          amount: null,
          img: null,
          status: "0",
          type: null,
          receptionId: null,
          receptionName: null,
          receptionTel: null,
          payStatus: "0",
          comment: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          remark: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加订单";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        this.type = 'edit'
        getOrder(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改订单";
        });
      },
      /** 领取订单操作按钮*/
      receiveUpdate(row) {
        updateOrder({
          id: row.id,
          status: 1,
          receptionId: this.$store.getters.userId,
          receptionName: this.$store.getters.userNickName,
          receptionTel: this.$store.getters.userTel
        }).then(response => {
          this.msgSuccess("领取成功");
          this.getList();
        })
      },
      /** 修改按钮操作 */
      view(row) {
        this.reset();
        const id = row.id || this.ids
        this.type = 'view'
        getOrder(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "查看订单";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateOrder(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addOrder(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除订单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有订单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
      }
    }
  };
</script>
